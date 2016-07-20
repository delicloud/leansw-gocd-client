package com.thoughtworks.lean.gocd.dto.history;

import java.util.ArrayList;
import java.util.List;

public class MaterialRevision {

    private List<Modification> modifications = new ArrayList<>();
    private Material material;
    private boolean changed;

    public List<Modification> getModifications() {
        return modifications;
    }

    public void setModifications(List<Modification> modifications) {
        this.modifications = modifications;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public boolean getChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isChanged() {
        return changed;
    }

    @Override
    public String toString() {
        return "MaterialRevision{" +
                "modifications=" + modifications +
                ", material=" + material +
                ", changed=" + changed +
                '}';
    }
}
