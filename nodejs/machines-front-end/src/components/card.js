import React from 'react';
import PropTypes from 'prop-types';
import {Link} from "react-router-dom";

const Card = ({image, actionName, actionPath}) => {

    return (
        <Link to={actionPath}>
            <div className="card action">
                <img src={image} alt="card image" className="image"/>
                <div className="container">
                    <h2><b>{actionName}</b></h2>
                </div>
            </div>
        </Link>
    );
};

Card.propTypes =
    {
        image: PropTypes.string.isRequired,
        actionName: PropTypes.string.isRequired,
        actionPath: PropTypes.string.isRequired
    }
;

export default Card;