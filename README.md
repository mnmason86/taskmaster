# Taskmaster

## Lab 26 9/19/22

### Features

Create a new app with three pages

**Main Activity**

Contains a plain text heading, an image, a button which redirects to the Add Task activity, and a button which redirects to the All Tasks activity
    
**Add Task**

Contains a plain text heading, an input for the task's title, an input for the task's description/body, and a submit button which, when clicked, renders a 'submitted' message.
  
**All Tasks**

Contains a plan text heading, an image, and a back button which redirects to the Main Activity view.

### Screenshots

![Home Page](screenshots/taskmaster_homepage.png)

![Add Task Page](screenshots/taskmaster_addtasks.png)

![All Tasks Page](screenshots/taskmaster_alltasks.png)

## Lab 27 9/20/22

### Features

**Task Detail Page**

Create a Task Detail page. It should have a title at the top of the page, and a Lorem Ipsum description.

**Settings Page**

Create a Settings page. It should allow users to enter their username and hit save.

**Home Page (Main Activity)**

The main page contains three different buttons with hardcoded task titles. When a user taps one of the titles, it goes to the Task Detail page, and the title at the top of the page match the task title that was tapped on the previous page.

The home page also contains a button to visit the Settings page, and once the user has entered their username, it displays “{username}’s tasks” above the three task buttons.

### Screenshots

![Home Page](screenshots/taskmaster_home2.png)

![Settings Page](screenshots/taskmaster_settings2.png)

![Task Details Page](screenshots/taskmaster_task_details2.png)

## Lab 28 9/21/22

### Features

**Task Class**

Task class contains a name, body, and a state ("new", "assigned", "in progress" or "complete).

**Home Page**

Refactored home page to use a RecyclerView for displaying Task data. Data is hardcoded.
Created TaskListRecycleViewAdapter that displays data from the hardcoded list of tasks.
When a task is tapped from the RecyclerView display, the Task Detail page is launched, with the name of the task displayed.

### Screenshots

![Home Page](screenshots/taskmaster_home3.png)

![Task Details Page](screenshots/taskmaster_task_details3.png)

## Lab 29 9/22/22

### Features

**Task Model and Room**

Room set up in application. Task class is annotated as an entity.

**Add Task Page**

Accepts data from the form and populates to the database

**Home Page**

Recycler View displays all tasks with title, body, and state from database. (updated 9/26/22)

**Task Detail Page**

Renders the name, body, and state of a task that is added from the Add Task Page (updated 9/26/22)

### Screenshots

![Task Details Page](screenshots/taskmaster_task_details4.png)


## Lab 31 9/26/22

### Features

Added Espresso testing to verify the following:
- Existence of elements on the Main Activity (home) page
- Existence of elements on the Add Details page
- The user can add a task, which is then rendered in the Recycler View on the home page

**Tried unsuccessfully to test username rendering to homepage after modifying on the settings page. Feature works properly, but testing was out-of-sync. May be due to timing issues**


## Lab 32 9/27/22

### Features

- Removed Rooms implementation.
- Initialized AWS Amplify
- Data is populating from the AWS database to the app. 
  - Home page Recycler View shows saved tasks.
  - Task Detail page shows a task from the Recycler View when it is tapped.

### Screenshots

![Home Page](screenshots/taskmaster_home5.png)

![Task Details Page](screenshots/taskmaster_task_details5.png)


## Lab 33 9/28/22

### Features

- Created entity of 'Team', which has a team name, and an assigned list of tasks. 
- Tasks are owned by a selected team
- Add Task form includes a Spinner which includes Team options
- Users can select their team on the Settings page.
- Team name is rendered on the Home page once selected

### Screenshots

![Homepage](screenshots/taskmaster_home6.png)

![Settings Page](screenshots/taskmaster_settings3.png)

## Lab 34

### Features

- Release app to the Google Play store
- Currently in review 10/10/22

![Play Store Review](screenshots/GooglePlay_releaseSS1.png)

## Lab 36 10/4/22

### Features

- AWS Cognito integrated to handle user Authentication and Verification
- Sign Up Activity accepts user input of email address and password, then redirects to verification activity
- Verification Activity accepts a verification code which is sent to the user via email, and redirects to the Login Activity
- Main Activity has buttons which redirect to Sign Up and Log In Activities
- Log In activity accepts user input of email address and password after a user has signed up and verified their email address.

### Screenshots

![Homepage](screenshots/taskmaster_home7.png)

![Sign Up Page](screenshots/taskmaster_signup7.png)

![Log In Page](screenshots/taskmaster_login7.png)

## Lab 37 10/12/22

## Features

- AWS S3 integrated to allow users to save downloaded images from the device to the Taskmaster app
- Settings Activity contains a button to direct users to the Image Activity, where users can create a new Team with a selected image
- Image Activity contains an EditText field for a new Team name, an 'Add Image Button', and a 'Save Team' button, which saves the newly crated Team and redirects the user back to the Settings page.

## Screenshots

![Settings Page](screenshots/taskmaster_settings8.png)

![Add Team Page](screenshots/taskmaster_addteam8.png)

![Image Downloads](screenshots/taskmaster_imgdl8.png)

![Add Team Page with Selected Image](screenshots/taskmaster_teamimg8.png)

## Lab 38 10/15/22

## Features

- Access user's location with android location service
- When a user adds a new task, their location is retrieved and included as part of the saved Task
- In the Task Detail Activity, the location of a Task is displayed it it exists

*issue*: upon clicking 'submit' from the Add Task activity, app crashes with error:
`Attempt to invoke virtual method 'double android.location.Location.getLatitude()' on a null object reference`

Location service is enabled on the emulated device, and allowed for the Taskmaster app.

## Screenshots

![Current Location Error](screenshots/location_error.png)

### Taskmaster App Attributions

Alex White - demo code
Lucy Gelderloos - GitHub repo
Ben Mills & Raul Zarate - help with debugging
Stanley Wilder - collaboration
