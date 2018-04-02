import React, {Component} from 'react';
import Button from './components/button';
import List from "./container/List/List";

let data = [
    {
        name: 'Test 1',
        members: ['ABCDSE', 'JANKD', 'ABCDSE', 'JANKD']
    },
    {
        name: 'Test 2',
        members: ['ABCDSE', 'JANKD']
    },
    {
        name: 'Test 3',
        members: ['ABCDSE', 'JANKD']
    }
];

class App extends Component {
    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-sm-4"><Button text={"Create Team"}/></div>
                    <div className="col-sm-4"><Button text={"Load Team"}/></div>
                    <div className="col-sm-4"><Button text={"Delete Team"}/></div>
                </div>
                <div className="row">
                    <div className="col-sm-12">
                        <List content={data}/>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;
