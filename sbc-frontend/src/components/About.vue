<template>
    <div>
        <b-button variant="primary" size="lg" @click="about"><span class="fa fa-info-circle"></span></b-button>

        <b-modal ref="about"
                 title="About sBC"
                 ok-only
                 ok-variant="info"
                 header-bg-variant='info'
                 header-text-variant='light'
                 header-close-label=''
                 scrollable>
            <p>This is sBC Rev. {{revision}}, {{date}}!</p>
            <p>sBC is a web-based app for searching ebooks collections.
                The collections are generated and maintained by eBC, the eBooks collection app, see <a
                        href="https://esb-dev.github.io/ebc.html" target="_blank">eBC Documentation</a>.
            </p>
            <p>sBC has two parts:</p>
            <ol>
                <li>A RESTful backend written in Kotlin and based on Javalin</li>
                <li>A frontend written in ECMAScript and based on Vue.js</li>

            </ol>
        </b-modal>
    </div>
</template>

<script>
    export default {
        name: "About",
        data() {
            return {
                revision: '2.0',
                date: '2021-11-01'
            }
        },
        methods: {
            about() {
                this.$refs['about'].show()
            }
        }
    }
</script>

<style scoped>

</style>