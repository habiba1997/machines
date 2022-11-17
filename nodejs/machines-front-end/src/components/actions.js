import changeLogo from './images/change.png';
import linkLogo from './images/link.webp';
import unlinkLogo from './images/unlink.png';
import machineOperations from "./images/machine-operation.webp";

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
    {
        name: "Machine Operation List",
        path: "/machine-operations",
        image: machineOperations
    },
];
export default actions;