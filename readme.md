# CMS/Blog

This is the backend for a Content Management System (CMS) and Blog application. It provides the necessary APIs for user authentication, blog post management, category, tag management, and commenting on blog posts.

## Database Structure

The backend is designed with the following database tables and fields:

### Users Table:
- **user_id** (Primary Key): Unique identifier for each user.
- **username**: User's username or display name.
- **email**: User's email address.
- **password**: Hashed password.


### Blog Post Table:
- **post_id** (Primary Key): Unique identifier for each blog post.
- **title**: Title of the blog post.
- **content**: The content of the blog post.
- **user_id** (Foreign Key): Links to the user_id in the Users table to associate each post with its author.
- **category_id** (Foreign Key): Links to the category_id in the Categories table to categorize the post.
- **created_at**: Timestamp for when the post was created.
- **updated_at**: Timestamp for when the post was last updated.

### Category Table:
- **category_id** (Primary Key): Unique identifier for each category.
- **name**: Name of the category (e.g., "Technology," "Travel," "Food," etc.).

### Tag Table:
- **tag_id** (Primary Key): Unique identifier for each tag.
- **name**: Name of the tag (e.g., "JavaScript," "React," "Cooking," etc.).

### Comment Table:
- **comment_id** (Primary Key): Unique identifier for each comment.
- **content**: The content of the comment.
- **user_id** (Foreign Key): Links to the user_id in the Users table to identify the comment author.
- **post_id** (Foreign Key): Links to the post_id in the Blog Posts table to associate the comment with a specific blog post.
- **created_at**: Timestamp for when the comment was created.
- **updated_at**: Timestamp for when the comment was last updated.

## API Endpoints

The backend provides the following API endpoints:

### User Authentication Endpoints:
- **POST /api/auth/register**: Allows users to register with a username, email, and password.
- **POST /api/auth/login**: Enables users to log in with their credentials.

### Blog Post Endpoints:
- **GET /api/posts**: Retrieves a list of all blog posts.
- **GET /api/posts/:id**: Retrieves a specific blog post by its ID.
- **POST /api/posts**: Creates a new blog post.
- **PUT /api/posts/:id**: Updates an existing blog post.
- **DELETE /api/posts/:id**: Deletes a blog post by its ID.

### Categories and Tags Endpoints:
- **GET /api/categories**: Retrieves a list of all blog post categories.
- **GET /api/categories/:id**: Retrieves a specific category by its ID.
- **GET /api/tags**: Retrieves a list of all blog post tags.
- **GET /api/tags/:id**: Retrieves a specific tag by its ID.

### Comments Endpoints:
- **GET /api/comments**: Retrieves a list of comments for a specific blog post.
- **POST /api/comments**: Creates a new comment on a blog post.
- **PUT /api/comments/:id**: Updates an existing comment.
- **DELETE /api/comments/:id**: Deletes a comment by its ID.

## Prerequisites

Before running the backend, ensure you have the following prerequisites:

- Java Development Kit (JDK) installed (e.g., OpenJDK 11 or higher).
- Apache Maven installed for building the project.
- MySQL or another compatible relational database set up.

## Getting Started

To set up and run the backend, follow these steps:

1. Clone this repository to your local machine:

   ```shell
   git clone https://github.com/GaneshDd/pikashoot.git

2. Navigate to the project directory
    ```shell
   cd pikashoot

3. Configure your database connection in 
    ```shell
   src/main/resources/application.properties.

4. Build this project using Maven.
    ```shell
   mvn clean install

5. Run the application
    ```shell
   mvn spring-boot:run
   
The backend should now be running locally and accessible through the specified API endpoints .

## Usage
* Access the application by navigating to http://localhost:8080 in your web browser.


