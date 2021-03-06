package wpn.hdri.ss.tango;

import com.google.common.base.MoreObjects;
import wpn.hdri.ss.data2.Attribute;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
* @author Igor Khokhriakov <igor.khokhriakov@hzg.de>
* @since 11.11.2015
*/
public class Context {
    public final String cid;
    public volatile boolean useAliases = false;
    public volatile boolean encode = false;
    public volatile OutputType outputType = OutputType.PLAIN;
    public volatile long lastTimestamp;
    public volatile AttributesGroup attributesGroup;

    private final Map<String, AttributesGroup> groups = new HashMap<>();

    /**
     * Creates default context
     * @param cid
     * @param attributes
     */
    public Context(String cid, Collection<Attribute<?>> attributes) {
        this.cid = cid;
        this.attributesGroup = new DefaultAttributesGroup(attributes);
        groups.put("default", this.attributesGroup);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cid", cid)
                .add("useAliases", useAliases)
                .add("encode", encode)
                .add("outputType", outputType)
                .add("lastTimestamp", lastTimestamp)
                .add("attributesGroup", attributesGroup)
                .toString();
    }

    public boolean hasGroup(String attributesGroup) {
        return groups.containsKey(attributesGroup);
    }

    public AttributesGroup getGroup(String attributesGroup) {
        return groups.get(attributesGroup);
    }

    public void setGroup(AttributesGroup attributesGroup) {
        groups.put(attributesGroup.name, attributesGroup);
        this.attributesGroup = attributesGroup;
    }

    public Iterable<String> getGroups(){
        return groups.keySet();
    }
}
