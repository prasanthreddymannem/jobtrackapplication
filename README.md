# Job Track Application

A full-stack web application to help job seekers organize and track their job applications in one place.

🔗 **Live Demo:** [Job Track Application](https://jobtrackapplication-qxau43yf2-prasanthreddymannem.vercel.app/)

💻 **Source Code:** [GitHub Repository](https://github.com/prasanthreddymannem/jobtrackapplication)

## About the Project

Managing multiple job applications can become difficult when tracking application statuses, company details, and progress manually.

I built this project to create a simple, centralized place to manage job applications while gaining practical experience in full-stack development, authentication, database integration, and deployment.

This is a personal learning project that I continue to improve.

## Features

- **User Authentication:** User registration and login with JWT-based authentication.
- **Job Application Management:** Create, view, update, and delete job application records.
- **Protected Access:** Spring Security and JWT authentication protect authorized application workflows.
- **Database Persistence:** PostgreSQL stores application data.
- **Frontend–Backend Integration:** The React frontend communicates with the Spring Boot backend.
- **Live Deployment:** The frontend and backend are deployed separately using free hosting services.

## Tech Stack

### Frontend
- React.js
- JavaScript
- Axios
- React Router
- Tailwind CSS

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Maven

### Database
- PostgreSQL

### Deployment and Tools
- Vercel — Frontend hosting
- Render — Backend hosting
- Git and GitHub — Version control

## How It Works

1. A user registers or logs in to the application.
2. The backend authenticates the user and uses JWT-based security for protected requests.
3. The frontend communicates with backend endpoints to manage job application records.
4. Application data is stored in PostgreSQL and retrieved when needed.

## Live Demo

You can try the application here:

**[Open Job Track Application](https://jobtrackapplication-qxau43yf2-prasanthreddymannem.vercel.app/)**

### Hosting Note

This project uses the free hosting options available on Vercel and Render.

The backend hosted on Render may become inactive after a period without requests. When it needs to start again, the first login or registration attempt may take longer than usual.

This is a limitation of the current hosting setup, not necessarily an application error. Please allow some time for the backend to start before retrying if the initial request is slow.

## Running the Project Locally

### Prerequisites

Install the following before running the project:

- Java version compatible with the project configuration (Java 25 is specified in the current Maven configuration)
- Maven, or use the included Maven Wrapper
- Node.js and npm
- PostgreSQL

### 1. Clone the Repository

```bash
git clone https://github.com/prasanthreddymannem/jobtrackapplication.git
cd jobtrackapplication
```

### 2. Configure the Database

Create a PostgreSQL database and configure the backend's database connection settings.

Set the database URL, username, password, and JWT secret using the configuration expected by the backend. Do not commit real credentials or secrets to GitHub.

### 3. Start the Backend

From the repository root, run:

```bash
./mvnw spring-boot:run
```

On Windows, use:

```bash
mvnw.cmd spring-boot:run
```

Ensure the database and required backend configuration are available before starting the application.

### 4. Start the Frontend

Open the frontend directory:

```bash
cd jobtrackfrontend
npm install
```

Run the development command defined in the frontend's `package.json`. Configure the frontend to use your locally running backend URL as required by the project.

## What I Learned

Building this project gave me practical experience with:

- Integrating a React frontend with a Java Spring Boot backend.
- Implementing authentication using Spring Security and JWT.
- Connecting application logic to a PostgreSQL database.
- Managing frontend and backend communication.
- Deploying a full-stack application using separate hosting platforms.
- Understanding deployment constraints and debugging integration issues.

## Future Improvements

- Improve error handling and loading states.
- Refine the user experience and responsiveness.
- Add more job application tracking and filtering options.
- Improve deployment reliability and configuration.
- Expand testing and documentation.

## Feedback

This is an independently developed project, and I'm continuing to learn and improve it.

Suggestions, feedback, and contributions are welcome!

**Developer:** [Prasanth Reddy Mannem](https://github.com/prasanthreddymannem)
