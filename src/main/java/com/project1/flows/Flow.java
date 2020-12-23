package com.project1.flows;

/**
 * Bean class to store flow details.
 */
public class Flow {

    /**
     * Stores the flow ID
     */
    private String ID;

    public Flow(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flow)) return false;
        Flow flow = (Flow) o;
        return ID.equals(flow.ID);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
