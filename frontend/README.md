# Schedule Management System - Frontend

A React-based frontend application for managing university schedules, groups, teachers, and subjects.

## Features

- **Groups Management**: Create, read, update, and delete groups
- **Teachers Management**: Manage teacher information
- **Subjects Management**: Handle subject data
- **Schedules Management**: Create and manage class schedules with relationships to groups, teachers, and subjects

## Prerequisites

- Node.js (v14 or higher)
- npm or yarn
- Backend API running on `http://localhost:8080`

## Installation

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

## Running the Application

Start the development server:
```bash
npm start
```

The application will open in your browser at `http://localhost:3000`.

## Project Structure

```
frontend/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── Groups.js
│   │   ├── Teachers.js
│   │   ├── Subjects.js
│   │   └── Schedules.js
│   ├── services/
│   │   └── api.js
│   ├── App.js
│   ├── App.css
│   ├── index.js
│   └── index.css
├── package.json
└── README.md
```

## API Configuration

The API base URL is configured in `src/services/api.js`. By default, it points to `http://localhost:8080`. You can modify this if your backend runs on a different port.

## Available Scripts

- `npm start` - Runs the app in development mode
- `npm build` - Builds the app for production
- `npm test` - Launches the test runner

## Usage

1. **Groups**: Navigate to the Groups tab to manage student groups
2. **Teachers**: Use the Teachers tab to manage teacher information
3. **Subjects**: Manage subjects in the Subjects tab
4. **Schedules**: Create schedules by selecting groups, teachers, and subjects in the Schedules tab

Each section supports full CRUD operations with a user-friendly interface.
