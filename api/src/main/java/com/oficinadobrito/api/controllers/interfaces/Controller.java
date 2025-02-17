package com.oficinadobrito.api.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Controller<T,C,U,ID> {
    ResponseEntity<T> postResource(@RequestBody C resource);
    ResponseEntity<T> getResourceById(@PathVariable("id") ID id);
    ResponseEntity<List<T>> getAllResource();
    ResponseEntity<T> updateResource(@PathVariable("id") ID id , @RequestBody U resource);
    ResponseEntity<Void> deleteResourceById(@PathVariable("id") ID id);
}
