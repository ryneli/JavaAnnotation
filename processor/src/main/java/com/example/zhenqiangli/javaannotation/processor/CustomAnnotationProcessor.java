package com.example.zhenqiangli.javaannotation.processor;

import java.io.IOException;
import java.io.WriteAbortedException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

// TODO: Do I need to switch to SourceVersion.RELEASE_8?
@SupportedAnnotationTypes("com.example.zhenqiangli.javaannotation.processor.CustomAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CustomAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        StringBuilder builder = new StringBuilder()
                .append("package com.example.zhenqiangli.javaannotation.generated;\n\n")
                .append("public class GeneratedClass {\n\n") // open class
                .append("\tpublic String getMessage() {\n") //open method
                .append("\t\treturn\"");
        for (Element element : roundEnvironment.getElementsAnnotatedWith(CustomAnnotation.class)) {
            String objectType = element.getSimpleName().toString();

            builder.append(objectType).append(" say hello!\\n");
        }

        builder.append("\";\n")     // end return
                .append("\t}\n")    // close method
                .append("}\n");     // close class

        try {
            JavaFileObject source = processingEnv.getFiler()
                    .createSourceFile("com.example.zhenqiangli.javaannotation.generated.GeneratedClass");
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            return true;
        }
        return true;
    }
}
