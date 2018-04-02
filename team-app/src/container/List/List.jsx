import React, {Component} from 'react';
import PropTypes from "prop-types";
import ListItem from "../../components/listitem";

class List extends Component {
    render() {
        return (
            <div className="list-group">
                {this.props.content.map((team) =>
                    <ListItem content={team}/>
                )
                }
            </div>
        );
    }
}

List.propTypes = {
    content: PropTypes.array.isRequired
};

List.defaultProps = {
    content: []
};

export default List;