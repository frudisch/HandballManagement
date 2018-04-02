import React, {Component} from 'react';
import PropTypes from 'prop-types';

class Card extends Component {
    render() {
        return (
            <div className="card">
                <img className="card-img-top" src="http://via.placeholder.com/500x200" alt="Test" />
                <div className="card-body">
                    <h4 className="card-title">{this.props.content.name}</h4>
                    <span>Members: </span>
                    <ul>{this.props.content.members.map((member) => <li>
                        <span>ID: </span><span>{member}</span></li>)}</ul>
                    <a href={"/team/" + this.props.content.name} className="btn btn-primary">See Team</a>
                </div>
            </div>
        )
    }
}

Card.propTypes = {
    content: PropTypes.object.isRequired
};

Card.defaultProps = {
    content: {
        name: 'Test',
        members: ['ABC', 'CDEF']
    }
};

export default Card;