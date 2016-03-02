app.controller('MainController', ['$scope', '$http', function($scope, $http) {

    $scope.playerHouses = [];
    $scope.playerStorage = null;
    $scope.opponentHouses = [];
    $scope.opponentStorage = null;
    $scope.gameId = null;
    $scope.houseId = null;
    $scope.houseId1 = 0;
    $scope.houseId2 = 1;
    $scope.houseId3 = 2;
    $scope.houseId4 = 3;
    $scope.houseId5 = 4;
    $scope.houseId6 = 5;

    initPath = '/new-game';
    joinPath = '/join?';
    doTurnPath = '/doTurn?';
    refreshPath = '/refresh?';

    $scope.doTurn = function(houseId) {
        myUrl = doTurnPath;
        myUrl += '&gameId=' + $scope.gameId;
        myUrl += '&houseId=' + houseId;

        var httpRequest = $http({
            method: 'GET',
            url: myUrl
        }).success(function(data, status) {
            $scope.playerHouses = data.playerHouses;
            $scope.playerStorage = data.playerStorage;
            $scope.opponentHouses = data.opponentHouses;
            $scope.opponentStorage = data.opponentStorage;
            $scope.gameId = data.gameId;
            $scope.playerTurn = data.playerTurn;
        });
    };

    $scope.join = function() {
        myUrl = joinPath;
        myUrl += '&gameId=' + $scope.gameId;

        var httpRequest = $http({
            method: 'GET',
            url: myUrl
        }).success(function(data, status) {
            $scope.playerHouses = data.playerHouses;
            $scope.playerStorage = data.playerStorage;
            $scope.opponentHouses = data.opponentHouses;
            $scope.opponentStorage = data.opponentStorage;
            $scope.gameId = data.gameId;
            $scope.playerTurn = data.playerTurn;
        });
    };

    $scope.refresh = function() {
        myUrl = refreshPath;
        myUrl += '&gameId=' + $scope.gameId;

        var httpRequest = $http({
            method: 'GET',
            url: myUrl
        }).success(function(data, status) {
            $scope.playerHouses = data.playerHouses;
            $scope.playerStorage = data.playerStorage;
            $scope.opponentHouses = data.opponentHouses;
            $scope.opponentStorage = data.opponentStorage;
            $scope.gameId = data.gameId;
            $scope.playerTurn = data.playerTurn;
        });
    };

    $scope.init = function() {
        myUrl = initPath;

        var httpRequest = $http({
            method: 'GET',
            url: myUrl
        }).success(function(data, status) {
            $scope.playerHouses = data.playerHouses;
            $scope.playerStorage = data.playerStorage;
            $scope.opponentHouses = data.opponentHouses;
            $scope.opponentStorage = data.opponentStorage;
            $scope.gameId = data.gameId;
            $scope.playerTurn = data.playerTurn;
        });
    };
}]);


