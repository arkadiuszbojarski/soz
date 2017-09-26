module.exports = function(grunt) {

  grunt.initConfig({
	pkg: grunt.file.readJSON('package.json'),
	ngAnnotate: {
    	options: {
    		remove: true,
    		add: true,
    		singleQuotes: true,
    		separator: ';',
    	},
    	dist: {
    		files: [{
    			expand: true,
    			src: [
    			      'src/main/resources/static/app/**/*.js',
    			      '!**/*min.js',
    			      ],
    			ext: '.annotated.js',
    			extDot: 'last',
    			dest: 'temp',
    		}],
    	}
    },
    concat: {
      options: {
        separator: ';',
      },
      dist: {
        src: [
              'temp/src/main/resources/static/app/app.annotated.js',
              'temp/src/main/resources/static/app/controllers.annotated.js',
              'temp/src/main/resources/static/app/services.annotated.js',
              'temp/src/main/resources/static/app/**/*.annotated.js',
              '!temp/src/main/resources/static/app/config.annotated.js',
              'temp/src/main/resources/static/app/config.annotated.js',
              '!**/*concat.js'
              ],
        dest: 'temp/src/main/resources/static/app/js/<%= pkg.name %>.concat.js',
      },
      bower: {
    	  src: [
    	        'src/main/resources/static/bower_components/angular/angular.js',
    	        'src/main/resources/static/bower_components/angular-cookies/angular-cookies.js',
    	        'src/main/resources/static/bower_components/angular-resource/angular-resource.js',
    	        'src/main/resources/static/bower_components/angular-translate/angular-translate.js',
    	        'src/main/resources/static/bower_components/angular-translate-handler-log/angular-translate-handler-log.js',
    	        'src/main/resources/static/bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js',
    	        'src/main/resources/static/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js',
    	        'src/main/resources/static/bower_components/angular-translate-storage-local/angular-translate-storage-local.js',
    	        'src/main/resources/static/bower_components/angular-ui-router/release/angular-ui-router.js',
    	        'src/main/resources/static/bower_components/angular-ui-utils/ui-utils.js',
    	        'src/main/resources/static/bower_components/angular-bootstrap/ui-bootstrap-tpls.js'
    	        ],
    	  dest: 'temp/src/main/resources/static/bower/bower.js',
      },
    },
    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n',
        mangle: true,
        compress: true
      },
      dist: {
        files: {
          'src/main/resources/static/js/<%= pkg.name %>.min.js': ['<%= concat.dist.dest %>'],
          'src/main/resources/static/js/bower.min.js': ['<%= concat.bower.dest %>']
        }
      }
    },
    jshint: {
    	files: [
    	        'Gruntfile.js', 'src/main/resources/static/app/**/*.js',
    	        '!**/*.min.js',
    	        ],
    	options: {
    		asi: true,
    		globals: {
    			jQuery: true,
    			console: true,
    			module: true,
    			document: true,
    		}
    	},
    },
    clean: {
    	temp: ["temp"],
    },
    jsonlint: {
    	locale: {
    		src: ['src/main/resources/static/languages/*.json']
    	}
    },
  });

  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-jsonlint');
  grunt.loadNpmTasks('grunt-ng-annotate');

  grunt.registerTask('default', ['jshint', 'ngAnnotate', 'concat', 'uglify', 'clean']);
};