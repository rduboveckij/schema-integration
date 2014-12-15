package ntu.asu.rduboveckij.service;

import com.google.common.io.Files;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import ntu.asu.rduboveckij.api.ReportService;
import ntu.asu.rduboveckij.api.settings.ApplicationSettings;
import ntu.asu.rduboveckij.model.Report;
import ntu.asu.rduboveckij.model.external.AbstractModelItem;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Result;
import ntu.asu.rduboveckij.model.internal.TableIndex;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;

/**
 * @author andrus.god
 * @since 26.11.2014.
 */
@Service
public class JsonReportServiceImpl implements ReportService {
    public static final JsonSerializer<Model> MODEL_JSON_SERIALIZER = (src, type, context) -> {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", src.getName());
        jsonObject.add("elements", context.serialize(src.getElements()));
        return jsonObject;
    };

    public static final JsonSerializer<Model.Element> MODAL_ELEMENT_JSON_SERIALIZER = (src, type, context) -> {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", src.getName());
        jsonObject.add("attributes", context.serialize(src.getAttributes()));
        return jsonObject;
    };

    public static final JsonSerializer<Model.Attribute> MODAL_ATTRIBUTE_JSON_SERIALIZER = (src, type, context) -> {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", src.getName());
        return jsonObject;
    };

    public static final JsonSerializer<Result.Element> RESULT_ELEMENT_JSON_SERIALIZER = (src, type, context) -> {
        JsonObject jsonObject = getResultJson(src);
        jsonObject.add("attributes", context.serialize(src.getAttributes()));
        return jsonObject;
    };
    public static final JsonSerializer<Result.Attribute> RESULT_ATTRIBUTE_JSON_SERIALIZER = (src, type, context) -> getResultJson(src);
    @Inject
    private ApplicationSettings settings;

    private static <P extends AbstractModelItem, T extends Result<P>> JsonObject getResultJson(T src) {
        JsonObject jsonObject = new JsonObject();
        TableIndex<P> index = src.getIndex();
        jsonObject.addProperty("source", index.getSource().getName());
        jsonObject.addProperty("target", index.getTarget().getName());
        jsonObject.addProperty("score", src.getScore());
        return jsonObject;
    }

    @Override
    public void save(Report report) {
        String jsonReport = new GsonBuilder()
                .registerTypeAdapter(Model.class, MODEL_JSON_SERIALIZER)
                .registerTypeAdapter(Model.Element.class, MODAL_ELEMENT_JSON_SERIALIZER)
                .registerTypeAdapter(Model.Attribute.class, MODAL_ATTRIBUTE_JSON_SERIALIZER)
                .registerTypeAdapter(Result.Element.class, RESULT_ELEMENT_JSON_SERIALIZER)
                .registerTypeAdapter(Result.Attribute.class, RESULT_ATTRIBUTE_JSON_SERIALIZER)
                .setPrettyPrinting()
                .create()
                .toJson(report);
        String name = report.getFirstModel().getName() + report.getSecondModel().getName() +
                Instant.now().toEpochMilli() + ".json";
        try {
            Files.write(jsonReport, settings.getReportSaveFolder().resolve(name).toFile(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}