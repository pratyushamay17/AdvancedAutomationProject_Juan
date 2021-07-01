package com.planittesting.automation.tests.dataProviders;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import com.planittesting.automation.model.data.ContactData;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.yaml.snakeyaml.Yaml;

public class YamlContactDataProvider implements ArgumentsProvider {

    @SuppressWarnings("unchecked")
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        String yamlFilePath = context.getTestMethod().get().getAnnotation(WithTestFile.class).value();
        InputStream yamlFile = getClass().getClassLoader().getResourceAsStream(yamlFilePath);
        var contactDataList = new LinkedList<Object>();
        new Yaml().loadAll(yamlFile).forEach(contactDataList::add);
        var data = ((HashMap<String, List<HashMap<String, String>>>)contactDataList.get(0)).get("data");
        return data.stream().map(entry ->
            new ContactData(entry.get("forename"),
                            entry.get("surname"),
                            entry.get("email"),
                            entry.get("telephone"),
                            entry.get("message"))
        ).map(Arguments::of);
    }
    
}
