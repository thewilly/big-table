package io.github.thewilly.bigtable.core.models;

public class TimeSeriesOrderedData implements DataVersion {

    private final byte[] _data;
    private final long _timestamp;
    private boolean isValid = true;

    public TimeSeriesOrderedData(byte[] data) {
        _data = data;
        _timestamp = System.currentTimeMillis();
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public void invalidate() {
        isValid = false;
    }

    @Override
    public long getTimestampt() {
        return _timestamp;
    }

    @Override
    public byte[] get() {
        return _data;
    }
}
