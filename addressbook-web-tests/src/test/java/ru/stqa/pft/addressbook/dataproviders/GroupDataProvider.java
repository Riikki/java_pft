package ru.stqa.pft.addressbook.dataproviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupDataProvider {

    @DataProvider
    public static Iterator<Object[]> validGroupsFromXml() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>)xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public static Iterator<Object[]>validGroupsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json,new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    public GroupData getOneGroupFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json,new TypeToken<List<GroupData>>(){}.getType());
        GroupData group = groups.get(0);
        return group;
    }
}