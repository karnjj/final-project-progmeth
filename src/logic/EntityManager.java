package logic;

import entity.base.Entity;

import java.util.*;

public class EntityManager {

    private static final ArrayList<Entity> allEntity = new ArrayList<>();

    private static final HashSet<Entity> addEntity = new HashSet<>();

    private static final HashSet<Entity> removeEntity = new HashSet<>();

    public static void addEntities(Entity... entities) {
        if (entities.length > 1) {
            addEntity.addAll(Arrays.asList((Entity[]) entities));
        } else {
            addEntity.add(entities[0]);
        }
    }

    public static void addEntitiesToBeRemoved(Entity... entities) {
        if (entities.length > 1) {
            removeEntity.addAll(Arrays.asList((Entity[]) entities));
        } else {
            removeEntity.add(entities[0]);
        }
    }

    public static void cleanupEntities() {
        allEntity.removeAll(removeEntity);
        allEntity.addAll(addEntity);
        removeEntity.clear();
        addEntity.clear();
        allEntity.sort(Comparator.comparing(Entity::getY));
    }
    
    public static void clear() {
    	allEntity.clear();
    	addEntity.clear();
    	removeEntity.clear();
    }

    public static ArrayList<Entity> getAllEntity() {
        return allEntity;
    }

    public static HashSet<Entity> getAddEntity() {
        return addEntity;
    }

    public static HashSet<Entity> getRemoveEntity() {
        return removeEntity;
    }
}
