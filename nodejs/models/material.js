function Material({ name, measuredValue, baseUnit }) {
  this.measuredValue = measuredValue || null;
  this.name = name || null;
  this.baseUnit = baseUnit || null;
}

Material.prototype.getMeasuredValue = function () {
  return this.measuredValue;
};

Material.prototype.setMeasuredValue = function (measuredValue) {
  this.measuredValue = measuredValue;
};

Material.prototype.getName = function () {
  return this.name;
};

Material.prototype.setName = function (name) {
  this.name = name;
};

Material.prototype.equals = function (otherMaterial) {
  return otherMaterial.getName() == this.getName();
};

Material.prototype.fill = function (newFields) {
  for (var field in newFields) {
    if (this.hasOwnProperty(field) && newFields.hasOwnProperty(field)) {
      if (this[field] !== 'undefined') {
        this[field] = newFields[field];
      }
    }
  }
};

module.exports = Material;
