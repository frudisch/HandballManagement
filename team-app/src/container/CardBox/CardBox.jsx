import React, {Component} from 'react';
import PropTypes from "prop-types";
import Card from "../../components/card/Card";

class CardBox extends Component {
    render() {
        return this.props.content.map((team) =>
            <div className="col-sm-3"><Card content={team}/></div>
        );
    }
}

CardBox.propTypes = {
    content: PropTypes.array.isRequired
};

CardBox.defaultProps = {
    content: []
};

export default CardBox;