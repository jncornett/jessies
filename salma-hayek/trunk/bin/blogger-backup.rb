#!/usr/bin/ruby -w

if ARGV.length() != 1
  print("usage: blogger-backup.rb <blog-name>\n")
  exit(1)
end

blog_name=ARGV[0]
blog_uri="http://#{blog_name}.blogspot.com/"

print("Downloading #{blog_uri}...\n")
blog_content=`curl #{blog_uri}`

blog_content.split("\n").each() {
  |line|
  if line =~ /"(#{blog_uri}(\d+_\d+_\d+_\S+_archive\.html))"/
    next_archive_uri=$1
    next_filename=$2
    system("curl -o #{next_filename} #{next_archive_uri}")
  end
}
