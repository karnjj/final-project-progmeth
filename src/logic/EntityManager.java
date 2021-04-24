package logic;

import entity.base.Entity;

import java.util.*;

public class EntityManager {

    private static List<Entity> allEntity = new ArrayList<>();

    private static Set<Entity> addEntity = new HashSet<>();

    private static Set<Entity> removeEntity = new HashSet<>();

    public List<Entity> getAllEntity() {
        return allEntity;
    }

    public void addEntities(Entity... entities) {
        if (entities.length > 1) {
            addEntity.addAll(Arrays.asList((Entity[]) entities));
        } else {
            addEntity.add(entities[0]);
        }
    }

    public void removeEntities(Entity... entities) {
        allEntity.removeAll(Arrays.asList(entities));
    }

    public Set<Entity> getEntitiesToBeRemoved() {
        return removeEntity;
    }

    public void addEntitiesToBeRemoved(Entity... entities) {
        if (entities.length > 1) {
            removeEntity.addAll(Arrays.asList((Entity[]) entities));
        } else {
            removeEntity.add(entities[0]);
        }
    }

    public void cleanupEntities() {
        allEntity.removeAll(removeEntity);
        allEntity.addAll(addEntity);
        removeEntity.clear();
        addEntity.clear();
        allEntity.sort(Comparator.comparing(Entity::getY));
    }
    
    public void clear() {
    	allEntity.clear();
    	addEntity.clear();
    	removeEntity.clear();
    }
}
