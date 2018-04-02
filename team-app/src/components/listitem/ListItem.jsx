import React, {Component} from 'react';
import PropTypes from 'prop-types';

class ListItem extends Component {
    render() {
        return (
            <div className="list-group-item">
                <span>Name: </span><span>{this.props.content.name}</span><br/>
                <span>Members: </span>
                <ul>{this.props.content.members.map((member) => <li>
                    <span>ID: </span><span>{member}</span></li>)}</ul>
            </div>
        )
    }
}

ListItem.propTypes = {
    content: PropTypes.object.isRequired
};

ListItem.defaultProps = {
    content: {
        name: 'Test',
        members: ['ABC', 'CDEF']
    }
};

export default ListItem;