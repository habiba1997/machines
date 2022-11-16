import React from 'react';
import Card from "./card";
import changeLogo from './images/change.png';
import linkLogo from './images/link.webp';
import unlinkLogo from './images/unlink.png';


const Landing = () => {
    const actions = [
        {
            name: "Change Operation Status",
            path: "/change-operation-status",
            image: changeLogo
        },
        {
            name: "Link Machine to Operation",
            path: "/link-machine-operation",
            image: linkLogo
        },
        {
            name: "Unlink Machine to Operation",
            path: "/unlink-machine-operation",
            image: unlinkLogo
        },
    ]
    return (
        <div className="container">
            {actions.map((action, index) => <Card key={index} actionPath={action.path} image={action.image}
                                           actionName={action.name}/>)}
        </div>
    );
};

export default Landing;