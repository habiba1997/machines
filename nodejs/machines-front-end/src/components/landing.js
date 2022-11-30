import React from 'react';
import Card from "./card";
import actions from "./actions";

const Landing = () => {

    return (
        <div className="container">
            {actions.map((action, index) => <Card key={index} actionPath={action.path} image={action.image}
                                           actionName={action.name}/>)}
        </div>
    );
};

export default Landing;