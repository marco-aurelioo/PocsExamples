#!/usr/bin/env bash
set -x
awslocal s3 mb s3://bucket/file
awslocal s3 cp /tmp/localstack/teste.txt s3://bucket/file/teste.txt
awslocal s3 ls s3://bucket/file/teste.txt
set +x