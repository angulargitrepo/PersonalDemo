package com.streamlinity.ct.springRestChallenge.solution;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamlinity.ct.springRestChallenge.api.Item;
import com.streamlinity.ct.springRestChallenge.api.SearchSvcInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Provide your implementation of the SearchSvcImpl here.
 * Also annotate your methods with Rest end point wrappers as required in the problem statement
 *
 * You can create any auxiliary classes or interfaces in this package if you need them.
 *
 * Do NOT add annotations as a Bean or Component or Service.   This is being handled in the custom Config class
 * PriceAdjustConfiguration
 */
public class SearchSvcImpl implements SearchSvcInterface {

    List<Item> itemPricesList;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void init(String itemPriceJsonFileName) {
        getFileFromResourcesByName(itemPriceJsonFileName);
    }

    @Override
    public void init(File itemPriceJsonFile) {
        getFileFromResourceByFile(itemPriceJsonFile);
    }

    @Override
    public List<Item> getItems() {
        return itemPricesList;
    }

    @Override
    public List<Item> getItems(String category) {
        List<Item> returnList =new ArrayList<>();
        returnList = itemPricesList.stream()
                    .filter(x-> category.equals(x.getCategory_short_name()))
                    .collect(Collectors.toList());
        return returnList;
    }

    @Override
    public List<Item> getItem(String itemShortName) {
        List<Item> returnList =new ArrayList<>();
        returnList = itemPricesList.stream()
                .filter(x-> itemShortName.equals(x.getShort_name()))
                .collect(Collectors.toList());
        return returnList;
    }

    public void getFileFromResourcesByName(String fileName){
            try{
                final File itemFile = applicationContext.getResource(fileName).getFile();
                ObjectMapper mapper = new ObjectMapper();
                itemPricesList = mapper.readValue(itemFile, new TypeReference<List<Item>>() {
                });
                System.out.println("===============> "+itemPricesList.size());
            }
            catch (IOException e){
                e.printStackTrace();
            }

    }

    public void getFileFromResourceByFile(File file){
        try{
            ObjectMapper mapper = new ObjectMapper();
            itemPricesList = mapper.readValue(file, new TypeReference<List<Item>>() {
            });
            System.out.println("===============> "+itemPricesList.size());
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
