const Location = require('./location');

function Machine({ name, machineType, location }) {
  // Accept name and age in the constructor
  this.machineType = machineType || null;
  this.name = name || null;
  const machineLocation = location && new Location(location);
  this.location = machineLocation || null;
}

Machine.prototype.getMachineType = function () {
  return this.machineType;
};

Machine.prototype.setAge = function (machineType) {
  this.machineType = machineType;
};

Machine.prototype.getName = function () {
  return this.name;
};

Machine.prototype.setName = function (name) {
  this.name = name;
};

Machine.prototype.equals = function (otherMachine) {
  return otherMachine.getName() == this.getName();
};

Machine.prototype.fill = function (newFields) {
  for (var field in newFields) {
    if (this.hasOwnProperty(field) && newFields.hasOwnProperty(field)) {
      if (this[field] !== 'undefined') {
        this[field] = newFields[field];
      }
    }
  }
};

module.exports = Machine;
