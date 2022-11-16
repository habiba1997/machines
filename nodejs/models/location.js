function Location({ key, name, temp, type }) {
  this.key = key || null;
  this.name = name || null;
  this.temp = temp || null;
  this.type = type || null;
}

Location.prototype.getKey = function () {
  return this.key;
};

Location.prototype.setAge = function (key) {
  this.key = key;
};

Location.prototype.getName = function () {
  return this.name;
};

Location.prototype.setName = function (name) {
  this.name = name;
};

Location.prototype.equals = function (otherLocation) {
  return (
    otherLocation.getName() == this.getName() &&
    otherLocation.getKey() == this.getKey()
  );
};

Location.prototype.fill = function (newFields) {
  for (var field in newFields) {
    if (this.hasOwnProperty(field) && newFields.hasOwnProperty(field)) {
      if (this[field] !== 'undefined') {
        this[field] = newFields[field];
      }
    }
  }
};

module.exports = Location;
