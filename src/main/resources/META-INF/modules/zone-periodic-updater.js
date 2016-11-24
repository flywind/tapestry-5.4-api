define(["jquery", "t5/core/zone"], function($, zoneManager) {

    return function(zoneElementId, eventURL, frequencySecs, maxUpdates) {

        var frequencyMillis = frequencySecs * 1000;
        var updatesCount = 0;

        var interval = setInterval(updateZone, frequencyMillis);

        function updateZone() {
            if (updatesCount++ < maxUpdates) {
                zoneManager.deferredZoneUpdate(zoneElementId, eventURL);
            }
            else {
                clearInterval(interval);
            }
        }
    }

});
