package org.jboss.wildscribe.site;

import org.jboss.dmr.Property;

/**
 * @author Stuart Douglas
 */
public class Attribute implements Comparable<Attribute> {
    private final String name;
    private final String description;
    private final String type;
    private final boolean nillable;
    private final boolean expressionsAllowed;
    private final String defaultValue;
    private final Integer min;
    private final Integer max;
    private final String accessType;
    private final String storage;
    private final Deprecated deprecated;

    public Attribute(String name, String description, String type, boolean nillable, boolean expressionsAllowed, String defaultValue, Integer min, Integer max, String accessType, String storage, Deprecated deprecated) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.nillable = nillable;
        this.expressionsAllowed = expressionsAllowed;
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
        this.accessType = accessType;
        this.storage = storage;
        this.deprecated = deprecated;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Attribute o) {
        return name.compareTo(o.getName());
    }

    public String getType() {
        return type;
    }

    public boolean isNillable() {
        return nillable;
    }

    public boolean isExpressionsAllowed() {
        return expressionsAllowed;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public String getAccessType() {
        return accessType;
    }

    public String getStorage() {
        return storage;
    }

    public Deprecated getDeprecated() {
        return deprecated;
    }

    public static Attribute fromProperty(final Property property) {
        String name = property.getName();
        String description = property.getValue().get("description").asString();
        String type = property.getValue().get("type").asString();
        boolean nilable = true;
        if (property.getValue().hasDefined("nillable")) {
            nilable = property.getValue().get("nillable").asBoolean();
        }
        String defaultValue = null;
        if (property.getValue().hasDefined("default")) {
            defaultValue = property.getValue().get("default").asString();
        }
        boolean expressionsAllowed = false;
        if (property.getValue().hasDefined("expressions-allowed")) {
            expressionsAllowed = property.getValue().get("expressions-allowed").asBoolean();
        }
        Integer min = null;
        if (property.getValue().hasDefined("min")) {
            min = property.getValue().get("min").asInt();
        }
        Integer max = null;
        if (property.getValue().hasDefined("max")) {
            max = property.getValue().get("max").asInt();
        }
        String accessType = property.getValue().get("access-type").asString();
        String storage = property.getValue().get("storage").asString();
        Attribute op = new Attribute(name, description, type, nilable, expressionsAllowed, defaultValue, min, max, accessType, storage, Deprecated.fromModel(property.getValue()));
        return op;
    }
}
