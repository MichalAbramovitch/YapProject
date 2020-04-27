import React from 'react';
import logo from './logo.svg';
import './App.css';
import FileSelector from './FileSelector';



function App() {
  return (
    <div className="YAP">
      <header className="header-yap">
        <p>
          please upload a file
        </p>
      </header>
      <FileSelector/>    
      </div>
  );
}

export default App;
