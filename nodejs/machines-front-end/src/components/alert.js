import PropTypes from 'prop-types';
import React from 'react';
import {connect} from 'react-redux';
import {closeAlert} from "../redux/actions/alert-action";

const Alert = ({alerts, closeAlert}) => {
    const close = (alert) => {
        closeAlert(alert.id);
    };
    return (
        alerts !== null &&
        alerts.length > 0 &&
        alerts.map((alert) => (
            <div key={alert.id} className={`alert alert-${alert.alertType}`}>
                {alert.msg}
                <i
                    icon='fas fa-times'
                    className='close float-right'
                    onClick={() => close(alert)}
                />
            </div>
        ))
    );
};

Alert.prototype = {
    // ptfr ( is a shortcut for is required)
    alerts: PropTypes.array.isRequired,
    closeAlert: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
    alerts: state.alert,
});

export default connect(mapStateToProps, {closeAlert})(Alert);
