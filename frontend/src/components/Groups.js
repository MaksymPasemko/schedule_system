import React, { useState, useEffect } from 'react';
import { groupsAPI } from '../services/api';
import '../App.css';

const Groups = () => {
  const [groups, setGroups] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const [editingGroup, setEditingGroup] = useState(null);
  const [formData, setFormData] = useState({
    name: '',
    createdAt: '',
    groupSize: '',
    groupLeader: '',
  });

  useEffect(() => {
    loadGroups();
  }, []);

  const loadGroups = async () => {
    try {
      setLoading(true);
      const response = await groupsAPI.getAll();
      setGroups(response.data);
      setError(null);
    } catch (err) {
      setError('Failed to load groups: ' + (err.response?.data?.message || err.message));
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
      const data = {
        name: formData.name,
        createdAt: formData.createdAt,
        groupSize: parseInt(formData.groupSize),
        groupLeader: formData.groupLeader,
      };

      if (editingGroup) {
        await groupsAPI.update(data);
        setSuccess('Group updated successfully!');
      } else {
        await groupsAPI.create(data);
        setSuccess('Group created successfully!');
      }

      resetForm();
      loadGroups();
      setTimeout(() => setSuccess(null), 3000);
    } catch (err) {
      setError('Failed to save group: ' + (err.response?.data?.message || err.message));
      setTimeout(() => setError(null), 5000);
    }
  };

  const handleEdit = (group) => {
    setEditingGroup(group);
    setFormData({
      name: group.name,
      createdAt: group.createdAt,
      groupSize: group.groupSize.toString(),
      groupLeader: group.groupLeader,
    });
    setShowForm(true);
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this group?')) {
      try {
        await groupsAPI.deleteById(id);
        setSuccess('Group deleted successfully!');
        loadGroups();
        setTimeout(() => setSuccess(null), 3000);
      } catch (err) {
        setError('Failed to delete group: ' + (err.response?.data?.message || err.message));
        setTimeout(() => setError(null), 5000);
      }
    }
  };

  const resetForm = () => {
    setFormData({
      name: '',
      createdAt: '',
      groupSize: '',
      groupLeader: '',
    });
    setEditingGroup(null);
    setShowForm(false);
  };

  if (loading) {
    return <div className="loading">Loading groups...</div>;
  }

  return (
    <div className="container">
      <div className="container-header">
        <h2>Groups</h2>
        <button className="btn btn-primary" onClick={() => setShowForm(!showForm)}>
          {showForm ? 'Cancel' : '+ Add Group'}
        </button>
      </div>

      {error && <div className="error">{error}</div>}
      {success && <div className="success">{success}</div>}

      {showForm && (
        <div className="form-container">
          <h3>{editingGroup ? 'Edit Group' : 'Create New Group'}</h3>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Name *</label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Created At *</label>
              <input
                type="date"
                name="createdAt"
                value={formData.createdAt}
                onChange={handleInputChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Group Size *</label>
              <input
                type="number"
                name="groupSize"
                value={formData.groupSize}
                onChange={handleInputChange}
                required
                min="1"
              />
            </div>
            <div className="form-group">
              <label>Group Leader *</label>
              <input
                type="text"
                name="groupLeader"
                value={formData.groupLeader}
                onChange={handleInputChange}
                required
              />
            </div>
            <div className="form-actions">
              <button type="submit" className="btn btn-primary">
                {editingGroup ? 'Update' : 'Create'}
              </button>
              <button type="button" className="btn btn-secondary" onClick={resetForm}>
                Cancel
              </button>
            </div>
          </form>
        </div>
      )}

      <div className="table-container">
        {groups.length === 0 ? (
          <div className="empty-state">
            <p>No groups found. Create your first group!</p>
          </div>
        ) : (
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Created At</th>
                <th>Group Size</th>
                <th>Group Leader</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {groups.map((group) => (
                <tr key={group.id}>
                  <td>{group.id}</td>
                  <td>{group.name}</td>
                  <td>{group.createdAt}</td>
                  <td>{group.groupSize}</td>
                  <td>{group.groupLeader}</td>
                  <td className="actions-cell">
                    <button
                      className="btn btn-sm btn-success"
                      onClick={() => handleEdit(group)}
                    >
                      Edit
                    </button>
                    <button
                      className="btn btn-sm btn-danger"
                      onClick={() => handleDelete(group.id)}
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

export default Groups;
