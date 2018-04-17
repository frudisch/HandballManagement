import * as React from 'react';

import './App.css';

class App extends React.Component {
  render() {
    return (
        <div className="row">
            <div className="col-sm">
                One of three columns
            </div>
            <div className="col-sm">
                One of three columns
            </div>
            <div className="col-sm">
                One of three columns
            </div>
        </div>
    );
  }
}

export default App;
