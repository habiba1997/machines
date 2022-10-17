const Material = require('./Material');
const ProductionOrder = require('./ProductionOrder');

function Operation({ name, status, material, productionOrder }) {
  // Accept name and age in the constructor
  this.status = status || null;
  this.name = name || null;
  this.material = (material && new Material(material)) || null;
  this.productionOrder =
    (productionOrder && new ProductionOrder(productionOrder)) || null;
}

Operation.prototype.getStataus = function () {
  return this.status;
};

Operation.prototype.setStatus = function (status) {
  this.status = status;
};

Operation.prototype.getName = function () {
  return this.name;
};

Operation.prototype.setName = function (name) {
  this.name = name;
};

Operation.prototype.equals = function (otherOperation) {
  return otherOperation.getName() == this.getName();
};

Operation.prototype.fill = function (newFields) {
  for (var field in newFields) {
    if (this.hasOwnProperty(field) && newFields.hasOwnProperty(field)) {
      if (this[field] !== 'undefined') {
        this[field] = newFields[field];
      }
    }
  }
};

module.exports = Operation;
