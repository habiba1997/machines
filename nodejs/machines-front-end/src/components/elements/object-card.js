import React from 'react';

const ObjectCard = ({image, object}) => {
    const keys = Object.keys(object);
    const upperCaseFirstLetter = (string) => {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }
    const validateValueExist = (key) => {
        if (object[key] !== null && object[key] !== undefined) {
            return object[key].toString();
        }
        return <i className="fas fa-times text-red"></i>;
    };
    return (<div className="card list">
        <img src={image} alt="icon" className="image"/>
        <div className="container">
            {keys.map((key, index) => (<h4 key={index}>
                <b className="bold text-primary">{upperCaseFirstLetter(key)}</b> : {validateValueExist(key)}
            </h4>))}
        </div>
    </div>);
};

export default ObjectCard;