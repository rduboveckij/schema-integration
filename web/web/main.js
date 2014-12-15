(function (angular) {
    "use strict";

    angular.module('schema.integration.report', [
        'ui.bootstrap'
    ]);

    var ALGORITHM_TYPES = {
        ALL: "All",
        SYNTACTIC: "Syntactic",
        SEMANTIC: "Semantic",
        DATA_TYPE: "DataType"
    };

    var findBySourceAndTarget = function (elements, source, target) {
        return _.findWhere(elements, {source: source, target: target});
    };

    var choseElementByType = function (config) {
        var type = config.type, report = config.report;
        if (type === ALGORITHM_TYPES.ALL) {
            return report.mainElements;
        } else if (type === ALGORITHM_TYPES.SYNTACTIC) {
            return report.syntacticElements;
        } else if (type === ALGORITHM_TYPES.SEMANTIC) {
            return report.dictionaryElements;
        } else if (type === ALGORITHM_TYPES.DATA_TYPE) {
            return report.dataTypeElements;
        } else {
            console.error(type, "is not supported");
        }
    };

    var MainCtrl = function ($scope) {
        $scope.config = {};
        $scope.table = {};

        $scope.config.type = ALGORITHM_TYPES.ALL;
        $scope.ALGORITHM_TYPES = ALGORITHM_TYPES;

        $scope.onReadReport = function (file) {
            $scope.config.report = angular.fromJson(file);
        };

        $scope.getCellValues = function (data, cols, row) {
            var rows = _.where(data, {target: row.name});
            return _.map(cols, function (col) {
                return _.find(rows, {source: col.name});
            });
        };

        $scope.selectTableCell = function (item) {
            $scope.table.element = item;
            $scope.config.isAttributeSelected = true;
        };

        $scope.returnToElementTable = function () {
            $scope.table.element = null;
            $scope.config.isAttributeSelected = false;
        };

        $scope.scoreToColor = function (score) {
            return '#'+ '4b' + Math.round((score * 135) + 100).toString(16) + '4b';
        };

        $scope.$watch("config", function (config) {
            var report = config.report;
            if (!report) {
                return false;
            }
            var firstModel = report.firstModel, secondModel = report.secondModel,
                cols = firstModel.elements, rows = secondModel.elements;

            var data = choseElementByType(config);
            if (config.isAttributeSelected) {
                var element = $scope.table.element,
                    source = element.source, target = element.target;
                cols = _.findWhere(cols, {name: source}).attributes;
                rows = _.findWhere(rows, {name: target}).attributes;
                data = findBySourceAndTarget(data, source, target).attributes;
                $scope.table.name = source + ' - ' + target;
            } else {
                $scope.table.name = firstModel.name + ' - ' + secondModel.name;
            }
            $scope.table.cols = cols;
            $scope.table.rows = rows;
            $scope.table.data = data;
        }, true);
    };

    angular.module('schema.integration.report')
        .controller('MainCtrl', [
            '$scope',
            MainCtrl
        ]);

    var OnReadFileLink = function (scope, element, attrs) {
        attrs.$observe('onReadFile', function (onReadFile) {
            element.off('change');
            element.on('change', function (onChangeEvent) {
                var reader = new FileReader();

                reader.onload = function (onLoadEvent) {
                    scope.$apply(function () {
                        scope[onReadFile](onLoadEvent.target.result);
                    });
                };

                reader.readAsText((onChangeEvent.srcElement || onChangeEvent.target).files[0]);
            });
        });
    };

    angular.module('schema.integration.report')
        .directive('onReadFile', [function () {
            return {
                restrict: 'A',
                scope: false,
                link: OnReadFileLink
            };
        }]);
})(angular);