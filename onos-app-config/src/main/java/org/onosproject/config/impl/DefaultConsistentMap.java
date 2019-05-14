package org.onosproject.config.impl;

import org.onosproject.store.service.ConsistentMap;
import org.onosproject.store.service.MapEventListener;
import org.onosproject.store.service.Versioned;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * TODO
 * @param <K>
 * @param <V>
 */
public class DefaultConsistentMap<K, V> implements ConsistentMap<K, V> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public Versioned<V> get(K key) {
        return null;
    }

    @Override
    public Versioned<V> getOrDefault(K key, V defaultValue) {
        return null;
    }

    @Override
    public Versioned<V> computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return null;
    }

    @Override
    public Versioned<V> compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public Versioned<V> computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public Versioned<V> computeIf(K key, Predicate<? super V> condition, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public Versioned<V> put(K key, V value) {
        return null;
    }

    @Override
    public Versioned<V> putAndGet(K key, V value) {
        return null;
    }

    @Override
    public Versioned<V> remove(K key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<Versioned<V>> values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, Versioned<V>>> entrySet() {
        return null;
    }

    @Override
    public Versioned<V> putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        return false;
    }

    @Override
    public boolean remove(K key, long version) {
        return false;
    }

    @Override
    public Versioned<V> replace(K key, V value) {
        return null;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    public boolean replace(K key, long oldVersion, V newValue) {
        return false;
    }

    @Override
    public void addListener(MapEventListener<K, V> listener, Executor executor) {

    }

    @Override
    public void removeListener(MapEventListener<K, V> listener) {

    }

    @Override
    public Map<K, V> asJavaMap() {
        return null;
    }

    @Override
    public Iterator<Map.Entry<K, Versioned<V>>> iterator() {
        return null;
    }
}
