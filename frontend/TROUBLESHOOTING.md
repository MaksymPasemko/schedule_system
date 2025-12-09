# Troubleshooting Guide

## Common Issues and Solutions

### 1. "Failed to load" errors

**Problem**: The frontend cannot connect to the backend API.

**Solutions**:
- Make sure your Spring Boot backend is running on `http://localhost:8080`
- Check if the backend server is accessible by opening `http://localhost:8080/group` in your browser
- Verify CORS is enabled in your backend (should have `@CrossOrigin(origins = "http://localhost:3000")`)

### 2. CORS Errors

**Problem**: Browser console shows CORS policy errors.

**Solutions**:
- Ensure all your controllers have `@CrossOrigin(origins = "http://localhost:3000")` annotation
- Check that the backend is running and accessible

### 3. "Cannot read property 'data' of undefined"

**Problem**: API response structure is unexpected.

**Solutions**:
- Check browser console for detailed error messages
- Verify the backend is returning data in the expected format
- Check network tab in browser DevTools to see the actual API response

### 4. Schedule creation fails

**Problem**: Creating a schedule returns an error.

**Solutions**:
- Make sure you have at least one Group, Teacher, and Subject created first
- Check that all required fields are filled (Group, Subject, Teacher, Pair Number, Shift, Weekday)
- Verify the IDs are valid (check that groups, teachers, and subjects exist)

### 5. Form validation issues

**Problem**: Forms don't submit or show validation errors.

**Solutions**:
- Check that all required fields (marked with *) are filled
- For date fields, use the date picker (format: YYYY-MM-DD)
- For number fields, enter valid numbers

### 6. Port conflicts

**Problem**: React app won't start or shows port already in use.

**Solutions**:
- Stop any other applications using port 3000
- Or set a different port: `PORT=3001 npm start`

### 7. Module not found errors

**Problem**: `npm start` shows module not found errors.

**Solutions**:
- Run `npm install` in the frontend directory
- Delete `node_modules` folder and `package-lock.json`, then run `npm install` again
- Make sure you're in the `frontend` directory when running commands

## Debugging Steps

1. **Check Browser Console**: Open DevTools (F12) and check the Console tab for errors
2. **Check Network Tab**: In DevTools, go to Network tab to see API requests and responses
3. **Check Backend Logs**: Look at your Spring Boot console for any errors
4. **Verify API Endpoints**: Test endpoints directly using Postman or browser:
   - `http://localhost:8080/group`
   - `http://localhost:8080/teacher`
   - `http://localhost:8080/subject`
   - `http://localhost:8080/schedule`

## Quick Test

To verify everything is working:

1. Start your backend: Run your Spring Boot application
2. Start frontend: `cd frontend && npm start`
3. Open browser: Go to `http://localhost:3000`
4. Create test data:
   - Create a Group
   - Create a Teacher
   - Create a Subject
   - Create a Schedule using the above

If all steps work, your setup is correct!
