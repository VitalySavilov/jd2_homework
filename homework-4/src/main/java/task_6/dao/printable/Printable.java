package task_6.dao.printable;

import base.entity.BaseEntity;

import java.io.Serializable;

public interface Printable<K extends Serializable, E extends BaseEntity<K>> {
    void savePrintId(E entity);
}
