import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "this is a gradle plugin, (*^__^*)……"
    }
}
