import React, { useState } from 'react';
import './App.css';
import Groups from './components/Groups';
import Teachers from './components/Teachers';
import Subjects from './components/Subjects';
import Schedules from './components/Schedules';

function App() {
  const [activeTab, setActiveTab] = useState('groups');

  const renderContent = () => {
    switch (activeTab) {
      case 'groups':
        return <Groups />;
      case 'teachers':
        return <Teachers />;
      case 'subjects':
        return <Subjects />;
      case 'schedules':
        return <Schedules />;
      default:
        return <Groups />;
    }
  };

  return (
    <div className="App">
      <header className="app-header">
        <h1>Schedule Management System</h1>
      </header>
      <nav className="app-nav">
        <button
          className={activeTab === 'groups' ? 'active' : ''}
          onClick={() => setActiveTab('groups')}
        >
          Groups
        </button>
        <button
          className={activeTab === 'teachers' ? 'active' : ''}
          onClick={() => setActiveTab('teachers')}
        >
          Teachers
        </button>
        <button
          className={activeTab === 'subjects' ? 'active' : ''}
          onClick={() => setActiveTab('subjects')}
        >
          Subjects
        </button>
        <button
          className={activeTab === 'schedules' ? 'active' : ''}
          onClick={() => setActiveTab('schedules')}
        >
          Schedules
        </button>
      </nav>
      <main className="app-main">
        {renderContent()}
      </main>
    </div>
  );
}

export default App;
