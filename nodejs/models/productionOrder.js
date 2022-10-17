const Material = require('./Material');

function ProductionOrder({ name, startDate, material, plannedQuantity }) {
  // Accept name and age in the constructor
  this.startDate = startDate || null;
  this.name = name || null;
  this.plannedQuantity = plannedQuantity || null;
  this.material = (material && new Material(material)) || null;
}

ProductionOrder.prototype.equals = function (otherMaterial) {
  return otherMaterial.getName() == this.getName();
};

ProductionOrder.prototype.fill = function (newFields) {
  for (var field in newFields) {
    if (this.hasOwnProperty(field) && newFields.hasOwnProperty(field)) {
      if (this[field] !== 'undefined') {
        this[field] = newFields[field];
      }
    }
  }
};

module.exports = ProductionOrder;
