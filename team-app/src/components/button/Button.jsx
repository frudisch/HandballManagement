import React, {Component} from 'react';
import PropTypes from 'prop-types';

class Button extends Component {
    render() {
        return (
            <button type="button" className="btn btn-primary">{this.props.text}</button>
        )
    }
}

Button.propTypes = {
    text: PropTypes.string.isRequired
};

Button.defaultProps = {
    text: 'Test'
};

export default Button;