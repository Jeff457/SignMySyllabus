import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Base64;

import com.docusign.esign.api.*;
import com.docusign.esign.client.*;
import com.docusign.esign.model.*;

public class DocuSign
{
    private String BaseUrl = "https://demo.docusign.net/restapi";
    private ApiClient apiClient;
    private List<LoginAccount> loginAccounts;
    private Random random = new Random();

    DocuSign()
    {
        apiClient = new ApiClient(BaseUrl);
        apiClient.addDefaultHeader("X-DocuSign-Authentication", AuthorizationHeader.INSTANCE.toString());

        // assign api client to the Configuration object
        Configuration.setDefaultApiClient(apiClient);

        try
        {
            // login call available off the AuthenticationApi
            AuthenticationApi authApi = new AuthenticationApi();

            // login has some optional parameters we can set
            AuthenticationApi.LoginOptions loginOps = authApi.new LoginOptions();
            loginOps.setApiPassword("true");
            loginOps.setIncludeAccountIdGuid("true");
            LoginInformation loginInfo = authApi.login(loginOps);

            // note that a given user may be a member of multiple accounts
            loginAccounts = loginInfo.getLoginAccounts();

            System.out.println("LoginInformation: " + loginAccounts);
        }
        catch (com.docusign.esign.client.ApiException ex)
        {
            System.out.println("Exception: " + ex);
        }
    }

    public String createEnvelope(String name, String email, String clientUserID)
    {
        // specify a document we want signed
        String SignTest1File = "[PATH/TO/DOCUMENT/TEST.PDF]";

        // create a byte array that will hold our document bytes
        byte[] fileBytes = null;

        try
        {
            String currentDir = System.getProperty("user.dir");

            // read file from a local directory
            Path path = Paths.get(currentDir + SignTest1File);
            fileBytes = Files.readAllBytes(path);
        }
        catch (IOException ioExcp)
        {
            // TODO: handle error
            System.out.println("Exception: " + ioExcp);
            return null;
        }

        // create an envelope that will store the document(s), field(s), and recipient(s)
        EnvelopeDefinition envDef = new EnvelopeDefinition();
        envDef.setEmailSubject("Please Sign Your Child's Syllabus");

        // add a document to the envelope
        Document doc = new Document();
        String base64Doc = Base64.getEncoder().encodeToString(fileBytes);
        doc.setDocumentBase64(base64Doc);
        doc.setName("GovSyllabus.pdf");    // can be different from actual file name
        doc.setDocumentId("1");

        List<Document> docs = new ArrayList<Document>();
        docs.add(doc);
        envDef.setDocuments(docs);

        // add a recipient to sign the document, identified by name and email we used above
        Signer signer = new Signer();
        signer.setEmail(email);
        signer.setName(name);
        signer.setRecipientId("1");

        // Must set |clientUserId| for embedded recipients and provide the same value when requesting
        // the recipient view URL in the next step
        signer.setClientUserId(clientUserID);

        // create a |signHere| tab somewhere on the document for the signer to sign
        // default unit of measurement is pixels, can be mms, cms, inches also
        SignHere signHere = new SignHere();
        signHere.setDocumentId("1");
        signHere.setPageNumber("2");
        signHere.setRecipientId("1");
        signHere.setXPosition("100");
        signHere.setYPosition("150");

        // can have multiple tabs, so need to add to envelope as a single element list
        List<SignHere> signHereTabs = new ArrayList<SignHere>();
        signHereTabs.add(signHere);
        Tabs tabs = new Tabs();
        tabs.setSignHereTabs(signHereTabs);
        signer.setTabs(tabs);

        // add recipients (in this case a single signer) to the envelope
        envDef.setRecipients(new Recipients());
        envDef.getRecipients().setSigners(new ArrayList<Signer>());
        envDef.getRecipients().getSigners().add(signer);

        // send the envelope by setting |status| to "sent". To save as a draft set to "created"
        envDef.setStatus("sent");

        try
        {
            // use the |accountId| we retrieved through the Login API to create the Envelope
            String accountId = loginAccounts.get(0).getAccountId();

            // instantiate a new EnvelopesApi object
            EnvelopesApi envelopesApi = new EnvelopesApi();

            // call the createEnvelope() API to make the signature request live and ready to be signed
            EnvelopeSummary envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);

            System.out.println("EnvelopeSummary: " + envelopeSummary);
            return envelopeSummary.getEnvelopeId();
        }
        catch (com.docusign.esign.client.ApiException ex)
        {
            System.out.println("Exception: " + ex);
        }

        return null;
    }


    public String getSigningUrl(String name) {

        int id = random.nextInt(9999);
        String email = name.replace(" ", "")+"@gmail.com";
        String envelopeId = this.createEnvelope(name, email, id+"");

        String accountId = loginAccounts.get(0).getAccountId();

        // instantiate a new EnvelopesApi object
        EnvelopesApi envelopesApi = new EnvelopesApi();

        // set the url where you want the recipient to go once they are done signing
        RecipientViewRequest returnUrl = new RecipientViewRequest();
        returnUrl.setReturnUrl("localhost:8080/workflow");
        returnUrl.setAuthenticationMethod("email");

        // recipient information must match embedded recipient info we provided in step #2
        returnUrl.setEmail(email);
        returnUrl.setUserName(name);
        returnUrl.setRecipientId("1");
        returnUrl.setClientUserId(id+"");

        // call the CreateRecipientView API then navigate to the URL to start the signing session
        ViewUrl recipientView;
        try {
            recipientView = envelopesApi.createRecipientView(accountId, envelopeId.toString(), returnUrl);

            System.out.println("ViewUrl: " + recipientView);

            return recipientView.getUrl();
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;


    }

}