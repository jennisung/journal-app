# authentication-system

## Lab 14
Create a server to protect site content by requiring users to sign up for an account and log in. Users should only be able to see the protected areas of site after they’ve logged in and established an authenticated session. Create the authentication system for this server on your own. Do not use Spring’s own built-in auth system. Display a user’s username somewhere on the page to indicate to a user that they’re logged in. Use Bcrypt to hash and check user passwords. Make sure not to return user’s passwords or their hashes, if you return user information in the response to any request.

### Feature Tasks
* Create a database.
* Create a table that models users with a username, id, and hashed password.
* Create a table that models posts with a postId, userId and text content.
* Create a table with a 1-to-many relationship between users and posts.
* Create login/signup page.
* Authenticate the site so only logged-in users can see posts.
* Unauthenticated users accessing protected areas of site should be redirected to login/signup page.
* Use Bcrypt to hash