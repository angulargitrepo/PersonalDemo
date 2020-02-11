package com.streamlinity.ct.springRestChallenge.solution;

import com.streamlinity.ct.springRestChallenge.api.Item;
import com.streamlinity.ct.springRestChallenge.api.SearchSvcInterface;
import com.streamlinity.ct.springRestChallenge.springConfiguration.SearchConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * This controller needs to expose the following rest endpoints.  You need to fill in the implementation here
 *
 * Required REST Endpoints
 *
 *      /item                       Get all items
 *      /item?category=C            Get all items in category specified by Category shortName
 *      /item/{itemShortName}       Get item that matches the specified Item shortName
 */

@Profile("default")
@RestController
public class SearchRestControllerImpl {

    @Autowired
    SearchSvcInterface searchSvc;

    @GetMapping(value = "/item")
    public List<Item> getItems(){
        return searchSvc.getItems();
    }

    @GetMapping(value = "/item" ,params = "category")
    public List<Item> getItems(@RequestParam(value = "category") String category){
        return searchSvc.getItems(category);
    }

    @GetMapping(value = "/item/{itemShortName}")
    public List<Item> getItem(@PathVariable String itemShortName){
        return searchSvc.getItem(itemShortName);
    }
}
