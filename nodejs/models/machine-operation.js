const Operation = require("./operation");
const Machine = require("./machine");

function MachineOperation({machine, operation}) {
  this.machine = new Machine(machine) || null;
  this.operation = new Operation(operation) || null;
}

MachineOperation.prototype.getMachine = function () {
  return this.machine;
};

MachineOperation.prototype.setMachine = function (machine) {
  this.machine = machine;
};

MachineOperation.prototype.getOperation = function () {
  return this.operation;
};

MachineOperation.prototype.setOperation = function (operation) {
  this.operation = operation;
};

MachineOperation.prototype.equals = function (otherMachineOperation) {
  return otherMachineOperation.getMachine() == this.getMachine();
};

MachineOperation.prototype.fill = function (newFields) {
  for (var field in newFields) {
    if (this.hasOwnProperty(field) && newFields.hasOwnProperty(field)) {
      if (this[field] !== 'undefined') {
        this[field] = newFields[field];
      }
    }
  }
};

module.exports = MachineOperation;
