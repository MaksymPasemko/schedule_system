import React, { useState, useEffect } from 'react';
import { schedulesAPI, groupsAPI, teachersAPI, subjectsAPI } from '../services/api';
import '../App.css';

const Schedules = () => {
  const [schedules, setSchedules] = useState([]);
  const [groups, setGroups] = useState([]);
  const [teachers, setTeachers] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const [editingSchedule, setEditingSchedule] = useState(null);
  const [formData, setFormData] = useState({
    id: null,
    groupId: '',
    subjectId: '',
    teacherId: '',
    pairNumber: '',
    shift: '',
    weekday: 'MONDAY',
  });

  const weekdays = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'];

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      setLoading(true);
      const [schedulesRes, groupsRes, teachersRes, subjectsRes] = await Promise.all([
        schedulesAPI.getAll(),
        groupsAPI.getAll(),
        teachersAPI.getAll(),
        subjectsAPI.getAll(),
      ]);
      setSchedules(schedulesRes.data);
      setGroups(groupsRes.data);
      setTeachers(teachersRes.data);
      setSubjects(subjectsRes.data);
      setError(null);
    } catch (err) {
      setError('Failed to load data: ' + (err.response?.data?.message || err.message));
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingSchedule) {
        const data = {
          id: formData.id,
          groupId: parseInt(formData.groupId),
          subjectId: parseInt(formData.subjectId),
          teacherId: parseInt(formData.teacherId),
          pairNumber: parseInt(formData.pairNumber),
          shift: formData.shift,
          weekday: formData.weekday,
        };
        await schedulesAPI.update(data);
        setSuccess('Schedule updated successfully!');
      } else {
        const data = {
          groupId: parseInt(formData.groupId),
          subjectId: parseInt(formData.subjectId),
          teacherId: parseInt(formData.teacherId),
          pairNumber: parseInt(formData.pairNumber),
          shift: formData.shift,
          weekday: formData.weekday,
        };
        await schedulesAPI.create(data);
        setSuccess('Schedule created successfully!');
      }

      resetForm();
      loadData();
      setTimeout(() => setSuccess(null), 3000);
    } catch (err) {
      setError('Failed to save schedule: ' + (err.response?.data?.message || err.message));
      setTimeout(() => setError(null), 5000);
    }
  };

  const handleEdit = (schedule) => {
    setEditingSchedule(schedule);
    setFormData({
      id: schedule.id,
      groupId: schedule.groupView.id.toString(),
      subjectId: schedule.subjectView.id.toString(),
      teacherId: schedule.teacherView.id.toString(),
      pairNumber: schedule.pairNumber.toString(),
      shift: schedule.shift,
      weekday: schedule.weekday,
    });
    setShowForm(true);
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this schedule?')) {
      try {
        await schedulesAPI.deleteById(id);
        setSuccess('Schedule deleted successfully!');
        loadData();
        setTimeout(() => setSuccess(null), 3000);
      } catch (err) {
        setError('Failed to delete schedule: ' + (err.response?.data?.message || err.message));
        setTimeout(() => setError(null), 5000);
      }
    }
  };

  const resetForm = () => {
    setFormData({
      id: null,
      groupId: '',
      subjectId: '',
      teacherId: '',
      pairNumber: '',
      shift: '',
      weekday: 'MONDAY',
    });
    setEditingSchedule(null);
    setShowForm(false);
  };

  if (loading) {
    return <div className="loading">Loading schedules...</div>;
  }

  return (
    <div className="container">
      <div className="container-header">
        <h2>Schedules</h2>
        <button className="btn btn-primary" onClick={() => setShowForm(!showForm)}>
          {showForm ? 'Cancel' : '+ Add Schedule'}
        </button>
      </div>

      {error && <div className="error">{error}</div>}
      {success && <div className="success">{success}</div>}

      {showForm && (
        <div className="form-container">
          <h3>{editingSchedule ? 'Edit Schedule' : 'Create New Schedule'}</h3>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Group *</label>
              <select
                name="groupId"
                value={formData.groupId}
                onChange={handleInputChange}
                required
              >
                <option value="">Select a group</option>
                {groups.map((group) => (
                  <option key={group.id} value={group.id}>
                    {group.name}
                  </option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>Subject *</label>
              <select
                name="subjectId"
                value={formData.subjectId}
                onChange={handleInputChange}
                required
              >
                <option value="">Select a subject</option>
                {subjects.map((subject) => (
                  <option key={subject.id} value={subject.id}>
                    {subject.name}
                  </option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>Teacher *</label>
              <select
                name="teacherId"
                value={formData.teacherId}
                onChange={handleInputChange}
                required
              >
                <option value="">Select a teacher</option>
                {teachers.map((teacher) => (
                  <option key={teacher.id} value={teacher.id}>
                    {teacher.name}
                  </option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>Pair Number *</label>
              <input
                type="number"
                name="pairNumber"
                value={formData.pairNumber}
                onChange={handleInputChange}
                required
                min="1"
              />
            </div>
            <div className="form-group">
              <label>Shift *</label>
              <input
                type="text"
                name="shift"
                value={formData.shift}
                onChange={handleInputChange}
                required
                placeholder="e.g., Morning, Afternoon"
              />
            </div>
            <div className="form-group">
              <label>Weekday *</label>
              <select
                name="weekday"
                value={formData.weekday}
                onChange={handleInputChange}
                required
              >
                {weekdays.map((day) => (
                  <option key={day} value={day}>
                    {day}
                  </option>
                ))}
              </select>
            </div>
            <div className="form-actions">
              <button type="submit" className="btn btn-primary">
                {editingSchedule ? 'Update' : 'Create'}
              </button>
              <button type="button" className="btn btn-secondary" onClick={resetForm}>
                Cancel
              </button>
            </div>
          </form>
        </div>
      )}

      <div className="table-container">
        {schedules.length === 0 ? (
          <div className="empty-state">
            <p>No schedules found. Create your first schedule!</p>
          </div>
        ) : (
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Group</th>
                <th>Subject</th>
                <th>Teacher</th>
                <th>Pair Number</th>
                <th>Shift</th>
                <th>Weekday</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {schedules.map((schedule) => (
                <tr key={schedule.id}>
                  <td>{schedule.id}</td>
                  <td>{schedule.groupView.name}</td>
                  <td>{schedule.subjectView.name}</td>
                  <td>{schedule.teacherView.name}</td>
                  <td>{schedule.pairNumber}</td>
                  <td>{schedule.shift}</td>
                  <td>{schedule.weekday}</td>
                  <td className="actions-cell">
                    <button
                      className="btn btn-sm btn-success"
                      onClick={() => handleEdit(schedule)}
                    >
                      Edit
                    </button>
                    <button
                      className="btn btn-sm btn-danger"
                      onClick={() => handleDelete(schedule.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default Schedules;
