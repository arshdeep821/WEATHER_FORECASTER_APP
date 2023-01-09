# Weather Forecaster App (Forecaster)

### Arshdeep Jaggo

### Project *Details* and *Description*:

I used Android technologies as well as a free open-source public API to create a weather application.
For this project, I had to deal with API integration in Java through Android Studio, which is not the same as API integration with just Java. 
I used the Android Volley library, which is a free library, in order to parse the JSON data in order to correctly display it to the user. 
Furthermore, instead of having multiple Activities, I decided to go with a multi-fragment display, which is similar to having multiple tabs on a web browser. 
I decided to use fragments so that each fragment can have a dedicated part of the data that comes from the API.
However, since fragments are not considered to be actual Activities in Android, I had to work around how I was going to deal with some parsing and technical issues.
I also utilize the user's location of their device, upon request, in order to determine the forecast of their current region, 
which increases the practicality of this overall application. Additionally, a user can also look up certain regions, such as cities and determine the forecast 
that way as well.

### Technologies Used ###
- Android Studio
- Open Weather Map API (Free Public API that provides information on weather around different parts of the world)

### Libraries Utilized ###
- Android Volley Library
- Android Picasso Library
