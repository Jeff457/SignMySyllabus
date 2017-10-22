![text](https://i.imgur.com/vndrtxy.jpg)

## Inspiration

Every year middle schools and high schools across the United States waste millions of pieces of paper on printing out the syllabus for each class. In those same schools, many students also struggle to have their parents sign the syllabus and return it on-time.

We wanted to fix these problems by giving schools an easy, paper-free way to manage class syllabi.

## What it does

Sign My Syllabus gives parents, teachers, and students an easy, paper free way to manage a class syllabus.

From a simple website, teachers can upload a syllabus for their course and share it with their students and parents. Teachers are able to view all of the students and parents who have signed the syllabus for each class. For those who haven't signed, teachers can send a reminder (through email or text message) to students and parents letting them know that they need to sign the course syllabus.

Students can use the website to sign the syllabus for each of their courses. They can also send a reminder (through email or text) to their parents letting them know that they need to sign the syllabus.

Parents can use the website to see and sign the syllabus for each class that their child is enrolled in.

## How we built it

We used Java, Tomcat, raw Javascript, CSS3, HTML5, and spent a bunch of time programming and designing.

## Challenges we ran into

We ran into a few small challenges while trying to use the Docusign Api, but those were easily conquered.

We also ran into a challenge with our build system. Originally we had our Tomcat deployment and testing environment set up on one laptop in Eclipse. However, we soon found many problems with that due to dependency management. We then had to create a new build and testing workflow from scratch to handle or Tomcat project. Using gradle, we were eventually able to cross this hurdle and our build system now works better than ever.

## Accomplishments that we're proud of

* The website looks good and functions properly.

* We managed to do a lot with just basic raw javascript, HTML, and CSS.

* We learned a lot about making a dynamic web application (especially with Tomcat)

## What we learned

Use dynamic web frameworks for any web application, even ones you think will be mostly static web pages.

## What's next for Sign My Syllabus

* Allow teachers to upload a syllabus, specify who needs to sign and where, then share it

* Expand Sign My Syllabus to work with more than just class syllabi. What if a student needs to be excused from school due to a sudden sickness, but the parents are unable to come to the school and sign the student out? We would

## Credits

### Jeff Stanton

The main reason why the projects works and looks so good. His ability to write dynamic webpages with raw javascript and Ajax continues to impress.

### Eric Wolfe

He came up with a good idea that ultimately was too complicated. Then he came up with this. He also did a little bit of programming in between his naps.
